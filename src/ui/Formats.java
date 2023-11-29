package ui;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Formats {
    public static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("##0.00%");
    public static final DecimalFormat VELOCITY_FORMAT = new DecimalFormat(" ##0.00 km/h;-##0.00 km/h");
    public static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat(" #0.00;-#0.00");
    public static final DateFormat TIME_FORMAT = new SimpleDateFormat("mm:ss.SSS");
}
