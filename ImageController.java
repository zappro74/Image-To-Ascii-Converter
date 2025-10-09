class ImageController
{
    private static double[][] originalPixels = ImageLoader.calculatePixels(ImageLoader.loadImage());
    private static int originalHeight = ImageLoader.getHeight();
    private static int originalWidth = ImageLoader.getWidth();
    private static int targetWidth = UserInput.getTargetWidth();
    private static int targetHeight = (int) (targetWidth * ((double) originalHeight / originalWidth));
    private static double[][] resizedPixels = new double[targetHeight][targetWidth];
    
    public static double[][] resizeOutput()
    {
        for (int y = 0; y < targetHeight; y++)
        {
            for (int x = 0; x < targetWidth; x++)
            {
                int sourceX = (int) (x * ((double) originalWidth / targetWidth) * .5);
                int sourceY = (int) (y * ((double) originalHeight / targetHeight) * .5);
                resizedPixels[y][x] = originalPixels[sourceY][sourceX];
            }
        }
        return resizedPixels;
    }
}