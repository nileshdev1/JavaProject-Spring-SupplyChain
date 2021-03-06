package org.nk.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.nk.model.Part;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class PartExcelView 
extends AbstractXlsxView
{
	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {
		//filename
		response.addHeader("Content-Disposition", "attachment;filename=parts.xlsx");
		Sheet sheet=workbook.createSheet("PARTS");
		
		@SuppressWarnings("unchecked")
		List<Part> plist=(List<Part>) model.get("list");
		setBody(plist,sheet);

	}

	private void setBody(List<Part> plist, Sheet sheet) {
		int count=0;
		for(Part p:plist) {
			Row r=sheet.createRow(count++);
			r.createCell(0).setCellValue(p.getPcode());
			r.createCell(1).setCellValue(p.getUomob().getUomModel());
			r.createCell(2).setCellValue(p.getoPurchaseOb().getOrderCode());
			r.createCell(3).setCellValue(p.getoSaleOb().getOrderCode());
		}
	}

}



