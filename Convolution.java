class Convolution
{
    private static int[][] originalPixels = ImageLoader.getPixels();
    private static int originalHeight = ImageLoader.getHeight();
    private static int originalWidth = ImageLoader.getWidth();
    private static int targetWidth = UserInput.getTargetWidth();
    private static int targetHeight = (int) (targetWidth * ((double) originalHeight / originalWidth));
    private static int[][] resizedPixels = new int[targetHeight][targetWidth];

    
}