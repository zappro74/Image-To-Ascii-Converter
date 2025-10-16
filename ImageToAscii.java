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
            case "DEFAULT":
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
}