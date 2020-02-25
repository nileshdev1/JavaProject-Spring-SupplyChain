package org.nk.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.nk.model.ShipmentType;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class ShipmentTypeExcelView extends AbstractXlsxView{
	

	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.addHeader("Content-Discription", "attachment;filename=shipment.xlsx");
		
		Sheet s=workbook.createSheet("ShipmentTypes");
		
		setHeader(s);
		
		@SuppressWarnings("unchecked")
		List<ShipmentType> list=(List<ShipmentType>)model.get("list");
		setdBody(s,list);
	}
	
	
	private void setHeader(Sheet s) {

		Row r=s.createRow(0);
		r.createCell(0).setCellValue("ID");
		r.createCell(1).setCellValue("MODE");
		r.createCell(2).setCellValue("CODE");
		r.createCell(3).setCellValue("ENABLED");
		r.createCell(4).setCellValue("GRADE");
		r.createCell(5).setCellValue("NOTE");
	}

	private void setdBody(Sheet s, List<ShipmentType> list) {

		int count=1;
		for(ShipmentType st:list) {
			
			Row r=s.createRow(count++);
			
			r.createCell(0).setCellValue(st.getShipId());
			r.createCell(1).setCellValue(st.getShipMode());
			r.createCell(2).setCellValue(st.getShipCode());
			r.createCell(3).setCellValue(st.getEnbShip());
			r.createCell(4).setCellValue(st.getShipGrade());
			r.createCell(5).setCellValue(st.getShipDesc());
		}
	}

	
	

}
