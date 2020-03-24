package com.tj.project.dto;

import java.sql.Date;

public class ReviewDto {
	private int reno;
	private String mid;
	private String aid;
	private int pno;
	private String retitle;
	private String recontent;
	private String rephoto;
	private String rephoto2;
	private int rehit;
	private int reref;
	private int rere_step;
	private int rere_level;
	private Date rerdate;
	private String pname;
	private String pphoto;

	public ReviewDto() {

	}

	public ReviewDto(int reno, String mid, String aid, int pno, String retitle, String recontent, String rephoto,
			String rephoto2, int rehit, int reref, int rere_step, int rere_level, Date rerdate, String pname,
			String pphoto) {

		this.reno = reno;
		this.mid = mid;
		this.aid = aid;
		this.pno = pno;
		this.retitle = retitle;
		this.recontent = recontent;
		this.rephoto = rephoto;
		this.rephoto2 = rephoto2;
		this.rehit = rehit;
		this.reref = reref;
		this.rere_step = rere_step;
		this.rere_level = rere_level;
		this.rerdate = rerdate;
		this.pname = pname;
		this.pphoto = pphoto;
	}

	public int getReno() {
		return reno;
	}

	public void setReno(int reno) {
		this.reno = reno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getRetitle() {
		return retitle;
	}

	public void setRetitle(String retitle) {
		this.retitle = retitle;
	}

	public String getRecontent() {
		return recontent;
	}

	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	public String getRephoto() {
		return rephoto;
	}

	public void setRephoto(String rephoto) {
		this.rephoto = rephoto;
	}

	public String getRephoto2() {
		return rephoto2;
	}

	public void setRephoto2(String rephoto2) {
		this.rephoto2 = rephoto2;
	}

	public int getRehit() {
		return rehit;
	}

	public void setRehit(int rehit) {
		this.rehit = rehit;
	}

	public int getReref() {
		return reref;
	}

	public void setReref(int reref) {
		this.reref = reref;
	}

	public int getRere_step() {
		return rere_step;
	}

	public void setRere_step(int rere_step) {
		this.rere_step = rere_step;
	}

	public int getRere_level() {
		return rere_level;
	}

	public void setRere_level(int rere_level) {
		this.rere_level = rere_level;
	}

	public Date getRerdate() {
		return rerdate;
	}

	public void setRerdate(Date rerdate) {
		this.rerdate = rerdate;
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

	@Override
	public String toString() {
		return "ReviewDto [reno=" + reno + ", mid=" + mid + ", aid=" + aid + ", pno=" + pno + ", retitle=" + retitle
				+ ", recontent=" + recontent + ", rephoto=" + rephoto + ", rephoto2=" + rephoto2 + ", rehit=" + rehit
				+ ", reref=" + reref + ", rere_step=" + rere_step + ", rere_level=" + rere_level + ", rerdate="
				+ rerdate + ", pname=" + pname + ", pphoto=" + pphoto + "]";
	}

}
