package com.tj.project.dto;

import java.sql.Date;

public class NoticeDto {
	private int nno;
	private String aid;
	private String ntitle;
	private String ncontent;
	private int nhit;
	private Date nrdate;

	public NoticeDto() {

	}

	public NoticeDto(int nno, String aid, String ntitle, String ncontent, int nhit, Date nrdate) {
		super();
		this.nno = nno;
		this.aid = aid;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nhit = nhit;
		this.nrdate = nrdate;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public int getNhit() {
		return nhit;
	}

	public void setNhit(int nhit) {
		this.nhit = nhit;
	}

	public Date getNrdate() {
		return nrdate;
	}

	public void setNrdate(Date nrdate) {
		this.nrdate = nrdate;
	}

	@Override
	public String toString() {
		return "NoticeDto [nno=" + nno + ", aid=" + aid + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", nhit="
				+ nhit + ", nrdate=" + nrdate + "]";
	}

}
