import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.*;

public class CGrep {

	 private static void solve(Executor e, Collection<Callable<Found>> solvers) throws InterruptedException, ExecutionException {
	     CompletionService<Found> ecs = new ExecutorCompletionService<Found>(e);
	     for (Callable<Found> s : solvers)
	         ecs.submit(s);
	     int n = solvers.size();
	     for (int i = 0; i < n; ++i) {
	         Found f = ecs.take().get();
	         if (f != null) {
	             System.out.println(f.getFileName() +" completed.");
	         }
	     }
	 }
	
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        String pattern = args[0];
        String[] fileNames = Arrays.copyOfRange(args, 1, args.length);
        Collection<Callable<Found>> solvers = new ArrayList<Callable<Found>>();
        for (String fileName: fileNames) {
            solvers.add(new GrepCallable(fileName, pattern));
        }
        ExecutorService e = Executors.newFixedThreadPool(3);
        solve(e, solvers);
    }
}
