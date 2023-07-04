
/**
 * Monthly appointment.
 */
public class Monthly extends Appointment {
   /**
    * Initializes appointment for a given date.
    * 
    * @param year        the year
    * @param month       the month
    * @param day         the day
    * @param description the text description of the appointment
    */
   public Monthly(int year, int month, int day, String description) {
      // -----------Start below here. To do: approximate lines of code = 1
      // Initialize the inherited variables - make use of the super() keyword
      // with proper parameters

      // -----------------End here. Please do not remove this comment. Reminder: no
      // changes outside the todo regions.
   }

   /**
    * Determines if the appointment occurs on the same day of the month.
    * 
    * @param year  the year
    * @param month the month
    * @param day   the day
    * @return true if day matches the appointment date and is later than the
    *         appointment date stored in this object
    */
   public boolean occursOn(int year, int month, int day) {
      // -----------Start below here. To do: approximate lines of code = 5
      // Override the occursOn() method. Check to see if the appointment occurs on the
      // same day of the month and is later than the appointment date stored in this
      // object .
      if (year < getYear()) {
         return false;
      }
      if (year == getYear()) {
         if (month < getMonth()) {
            return false;
         }
      }

      return day == getDay();

      // -----------------End here. Please do not remove this comment. Reminder: no
      // changes outside the todo regions.
   }
}
