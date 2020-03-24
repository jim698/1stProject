package com.tj.project.dto;

import java.sql.Date;

public class OrdersDto {
	private int ono;
	private String mid;
	private int pno;
	private String oaddress;
	private String otel;
	private int cnt;
	private int cost;
	private Date ordate;
	private String ostate;
	private String pname;
	private String pphoto;
	private String maddress;
	private String mtel;

	public OrdersDto() {

	}

	public OrdersDto(int ono, String mid, int pno, String oaddress, String otel, int cnt, int cost, Date ordate,
			String ostate, String pname, String pphoto, String maddress, String mtel) {

		this.ono = ono;
		this.mid = mid;
		this.pno = pno;
		this.oaddress = oaddress;
		this.otel = otel;
		this.cnt = cnt;
		this.cost = cost;
		this.ordate = ordate;
		this.ostate = ostate;
		this.pname = pname;
		this.pphoto = pphoto;
		this.maddress = maddress;
		this.mtel = mtel;
	}

	public int getOno() {
		return ono;
	}

	public void setOno(int ono) {
		this.ono = ono;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getOaddress() {
		return oaddress;
	}

	public void setOaddress(String oaddress) {
		this.oaddress = oaddress;
	}

	public String getOtel() {
		return otel;
	}

	public void setOtel(String otel) {
		this.otel = otel;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Date getOrdate() {
		return ordate;
	}

	public void setOrdate(Date ordate) {
		this.ordate = ordate;
	}

	public String getOstate() {
		return ostate;
	}

	public void setOstate(String ostate) {
		this.ostate = ostate;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPphoto() {
		return pphoto;
	}

	public void setPphoto(String pphoto) {
		this.pphoto = pphoto;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public String getMtel() {
		return mtel;
	}

	public void setMtel(String mtel) {
		this.mtel = mtel;
	}

	@Override
	public String toString() {
		return "OrdersDto [ono=" + ono + ", mid=" + mid + ", pno=" + pno + ", oaddress=" + oaddress + ", otel=" + otel
				+ ", cnt=" + cnt + ", cost=" + cost + ", ordate=" + ordate + ", ostate=" + ostate + ", pname=" + pname
				+ ", pphoto=" + pphoto + ", maddress=" + maddress + ", mtel=" + mtel + "]";
	}

}
