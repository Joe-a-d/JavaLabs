
public class Attack {

		private String atkName;
		private int atkPoints;
		
		public Attack(String atkName, int atkPoints) {
			this.atkName = atkName;
			this.atkPoints = atkPoints;
		}

		/**
		 * @return the atkName
		 */
		public String getAtkName() {
			return atkName;
		}


		/**
		 * @return the atkPoints
		 */
		public int getAtkPoints() {
			return atkPoints;
		}


		@Override
		public String toString() {
			return "Attack [Name=" + atkName + ", Points=" + atkPoints + "]";
		}
		
		
		
}
