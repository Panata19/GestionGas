package com.panata.cilindros.controller;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.panata.cilindros.entity.Gastos;
import com.panata.cilindros.reporting.RptCategoriaGastos;
import com.panata.cilindros.service.IGastosService;

@Controller
@RequestMapping(value = "/gastospdf")
public class GastosPDFReportController {
	
	@Autowired 
	private IGastosService srvGastos;
	
	@GetMapping(value="/crear")
	public @ResponseBody byte[] export(HttpServletResponse response, @RequestParam String since, @RequestParam String to) {
		
		response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=gastos_" + since + '_' + to + ".pdf";
        response.setHeader(headerKey, headerValue);
		
        
        byte[] contents = document(since, to);
        
		return contents;
	}
	
	public byte[] document(String since, String to) {
		
		List<RptCategoriaGastos> gastos = todasGastos(since, to);
		
		Document document = new Document(PageSize.A4.rotate(), 5f, 5f, 5f, 5f);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        PdfWriter.getInstance(document, stream);
        
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(16);
        
        Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontBold.setSize(14);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(14);
        
        
        document.open(); 
        

        PdfPTable table = new PdfPTable(2);
        
        Paragraph p = new Paragraph("Reporte de Gastos " + since + " - " + to, fontTitle);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        
        document.add(new Paragraph(" "));
        
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cell.setPhrase(new Phrase("Categoría", fontBold));
        table.addCell(cell);
        
        cell.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cell.setPhrase(new Phrase("Cantidad", fontBold));
        table.addCell(cell);
        
        for (RptCategoriaGastos gasto : gastos) {
        	cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        	cell.setPhrase(new Phrase(gasto.getCategoria(), font));
            table.addCell(cell);
            
            cell.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cell.setPhrase(new Phrase(Float.toString(gasto.getGasto()), font));
            table.addCell(cell);
		}
        
         
        document.add(table);
        
        
        document.close();
        
        
        return stream.toByteArray();
	}
	
	public List<RptCategoriaGastos> todasGastos(String since, String to) {				
		try {	
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
			List<Gastos> gastos = srvGastos.findGastosFiniFfin(since, to);
			List<String> categoria =  new ArrayList<String>();
			List<RptCategoriaGastos> reporte = new ArrayList<RptCategoriaGastos>();
			Float sumatoria=(float) 0;
			
			
			
			
			for(Gastos g:gastos) {
				categoria.add(g.getCategoria().getNombre());
			}
			categoria = categoria.stream().distinct().collect(Collectors.toList());
			
			//gastos de con la categoria resprectiva 
			
			for(String g:categoria) {
				sumatoria=(float) 0;
				for(int i=0 ; i<gastos.size();i++) {
					if(g==gastos.get(i).getCategoria().getNombre()) {
						sumatoria=sumatoria+gastos.get(i).getCantidad();
					}	
				}
				
				RptCategoriaGastos datos = new RptCategoriaGastos();
				datos.setCategoria(g);
				datos.setGasto(sumatoria);
				reporte.add(datos);
				
			}
			return reporte;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
}
