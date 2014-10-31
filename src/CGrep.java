import java.util.Arrays;
import java.util.concurrent.Callable;

public class CGrep {

    public static void main(String[] args){
        String pattern = args[0];
        String[] fileNames = Arrays.copyOfRange(args, 1, args.length);
        for (String fileName: fileNames) {
            // TODO I dunno. -Robert
        }
    }
}
