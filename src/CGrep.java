import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class CGrep {

	 private void solve(Executor e, Collection<Callable<Found>> solvers) throws InterruptedException, ExecutionException {
	     CompletionService<Found> ecs = new ExecutorCompletionService<Found>(e);
	     for (Callable<Found> s : solvers)
	         ecs.submit(s);
	     int n = solvers.size();
	     for (int i = 0; i < n; ++i) {
	         Found f = ecs.take().get();
	         if (f != null) {
	             //TODO do something with Found... print all matching lines?
	         }
	     }
	 }
	
    public static void main(String[] args){
        String pattern = args[0];
        String[] fileNames = Arrays.copyOfRange(args, 1, args.length);
        for (String fileName: fileNames) {
            //solve();
        }
    }
}
