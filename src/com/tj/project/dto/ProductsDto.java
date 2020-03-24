package com.tj.project.dto;

import java.sql.Date;

public class ProductsDto {
	private int pno;
	private String pkinds;
	private String pname;
	private String pphotologo;
	private String pphoto;
	private String pphoto2;
	private String pcontent;
	private int pcnt;
	private int pprice;
	private int pdiscount;
	private Date prdate;

	public ProductsDto() {

	}

	public ProductsDto(int pno, String pkinds, String pname, String pphotologo, String pphoto, String pphoto2,
			String pcontent, int pcnt, int pprice, int pdiscount, Date prdate) {

		this.pno = pno;
		this.pkinds = pkinds;
		this.pname = pname;
		this.pphotologo = pphotologo;
		this.pphoto = pphoto;
		this.pphoto2 = pphoto2;
		this.pcontent = pcontent;
		this.pcnt = pcnt;
		this.pprice = pprice;
		this.pdiscount = pdiscount;
		this.prdate = prdate;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPkinds() {
		return pkinds;
	}

	public void setPkinds(String pkinds) {
		this.pkinds = pkinds;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPphotologo() {
		return pphotologo;
	}

	public void setPphotologo(String pphotologo) {
		this.pphotologo = pphotologo;
	}

	public String getPphoto() {
		return pphoto;
	}

	public void setPphoto(String pphoto) {
		this.pphoto = pphoto;
	}

	public String getPphoto2() {
		return pphoto2;
	}

	public void setPphoto2(String pphoto2) {
		this.pphoto2 = pphoto2;
	}

	public String getPcontent() {
		return pcontent;
	}

	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public int getPcnt() {
		return pcnt;
	}

	public void setPcnt(int pcnt) {
		this.pcnt = pcnt;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public int getPdiscount() {
		return pdiscount;
	}

	public void setPdiscount(int pdiscount) {
		this.pdiscount = pdiscount;
	}

	public Date getPrdate() {
		return prdate;
	}

	public void setPrdate(Date prdate) {
		this.prdate = prdate;
	}

	@Override
	public String toString() {
		return "ProductsDto [pno=" + pno + ", pkinds=" + pkinds + ", pname=" + pname + ", pphotologo=" + pphotologo
				+ ", pphoto=" + pphoto + ", pphoto2=" + pphoto2 + ", pcontent=" + pcontent + ", pcnt=" + pcnt
				+ ", pprice=" + pprice + ", pdiscount=" + pdiscount + ", prdate=" + prdate + "]";
	}

}
