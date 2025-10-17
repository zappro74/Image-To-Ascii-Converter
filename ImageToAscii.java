import java.util.ArrayList;

class ImageToAscii
{
    private static final String DEFAULT_RAMP = "@%#*+=-:.";
    private static final String LIGHT_RAMP = ".:-=+*#%@";
    private static final String DENSE_RAMP = "$@B%8&WM#*oahkbdpqwmZ0OQLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'.";

    public static ArrayList<String> toAsciiLines(double[][] brightness, String rampChoice)
    {
        ArrayList<String> lines = new ArrayList<>(brightness.length);
        String ramp;
        switch (rampChoice.toUpperCase())
        {
            case "SIMPLE":
                ramp = DEFAULT_RAMP;
                break;
            case "LIGHT":
                ramp = LIGHT_RAMP;
                break;
            case "DENSE":
            default:
                ramp = DENSE_RAMP;
                break;
        }

        for (int y = 0; y < brightness.length; y++)
        {
            StringBuilder row = new StringBuilder(brightness[0].length);
            for (int x = 0; x < brightness[0].length; x++)
            {
                double value = brightness[y][x];
                int index = (int) Math.round((value / 255.0) * (ramp.length() - 1));
                index = Math.max(0, Math.min(index, ramp.length() - 1));

                row.append(ramp.charAt(index));
            }

            lines.add(row.toString());
        }

        return lines;
    }

    public static ArrayList<String> toAsciiLinesColored(double[][] brightness, double[][][] rgb, String rampChoice)
    {
        ArrayList<String> lines = new ArrayList<>(brightness.length);
        String ramp;

        switch (rampChoice.toUpperCase())
        {
            case "SIMPLE":
                ramp = DEFAULT_RAMP;
                break;
            case "LIGHT":
                ramp = LIGHT_RAMP;
                break;
            case "DENSE":
            default:
                ramp = DENSE_RAMP;
                break;
        }

        for (int y = 0; y < brightness.length; y++)
        {
            StringBuilder row = new StringBuilder(brightness[0].length * 20); 
            for (int x = 0; x < brightness[0].length; x++)
            {

                double value = brightness[y][x];
                if (value < 0) 
                {
                    value = 0;
                }
                else if (value > 255) 
                {
                    value = 255;
                }

                int index = (int) Math.round((value / 255.0) * (ramp.length() - 1));
                index = Math.max(0, Math.min(index, ramp.length() - 1));
                char asciiChar = ramp.charAt(index);

                double r = rgb[y][x][0];
                double g = rgb[y][x][1];
                double b = rgb[y][x][2];

                if (r < 0) 
                {
                    r = 0;
                } 
                else if (r > 255) 
                {
                    r = 255;
                }

                if (g < 0) 
                {
                    g = 0;
                }
                else if (g > 255) 
                {
                    g = 255;
                }

                if (b < 0)
                {
                    b = 0;
                } 
                else if (b > 255) 
                {
                    b = 255;
                }

                int red = (int) Math.round(r);
                int green = (int) Math.round(g);
                int blue = (int) Math.round(b);

                //appended 'r' instead of the new 'red'... now it prints in color. RIP 10 points :( But hey, It works :)
                row.append("\u001B[38;2;").append(red).append(";").append(green).append(";").append(blue).append("m").append(asciiChar).append("\u001B[0m");
                }

            lines.add(row.toString());
        }
        return lines;
    }
}