package com.companyoftim.beans;

import java.sql.Date;

public class Event {
		private String desc;
		private String loc;
		private Date evttime;
		
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getLoc() {
			return loc;
		}
		public void setLoc(String loc) {
			this.loc = loc;
		}
		public Date getEvttime() {
			return evttime;
		}
		public void setEvttime(Date evttime) {
			this.evttime = evttime;
		}
}
