import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class CGrep {

	 private static void solve(Executor e, Collection<Callable<Found>> solvers) throws InterruptedException, ExecutionException {
	     ExecutorCompletionService<Found> ecs = new ExecutorCompletionService<Found>(e);
	     int n = solvers.size();
	     List<Future<Found>> futures
	         = new ArrayList<Future<Found>>(n);
	     Found result = null;
	     for (Callable<Found> s : solvers)
	         futures.add(ecs.submit(s));
	     for (Future<Found> f : futures) {
	    	 result = f.get();
	         if (result != null) {
	             System.out.println(result.getFileName() +" completed.");
	             System.out.println(result);
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
        e.shutdown();
    }
}