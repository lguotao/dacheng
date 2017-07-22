package com.dacheng.mes.sfdcs.pojo;

public class DataCollector {

		private int scannerKey ; 
		
		private int deviceNumber;
		
		private int sfdsKey;
		
		private String sfdsId;
		
		private int locationKey;
		
		private String description;
		
		private boolean inactive;

		public int getScannerKey() {
			return scannerKey;
		}

		public void setScannerKey(int scannerKey) {
			this.scannerKey = scannerKey;
		}

		public int getDeviceNumber() {
			return deviceNumber;
		}

		public void setDeviceNumber(int deviceNumber) {
			this.deviceNumber = deviceNumber;
		}

		public int getSfdsKey() {
			return sfdsKey;
		}

		public void setSfdsKey(int sfdsKey) {
			this.sfdsKey = sfdsKey;
		}

		public String getSfdsId() {
			return sfdsId;
		}

		public void setSfdsId(String sfdsId) {
			this.sfdsId = sfdsId;
		}

		public int getLocationKey() {
			return locationKey;
		}

		public void setLocationKey(int locationKey) {
			this.locationKey = locationKey;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public boolean isInactive() {
			return inactive;
		}

		public void setInactive(boolean inactive) {
			this.inactive = inactive;
		}
		
}
