/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package date_chooser;

import com.raven.datechooser.DateChooser;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author kietl
 */
public class Chooser {

    private DateChooser date = new DateChooser();

    public Chooser() {
    }

    public void defaultFormat(JTextField txt) {
        date.setTextField(txt);
        date.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        date.setLabelCurrentDayVisible(false);
    }

    public String formatDateToSQL(String str) {
        DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date d = inputFormat.parse(str);
             return outputFormat.format(d);
        } catch (ParseException ex) {
            Logger.getLogger(Chooser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
}
