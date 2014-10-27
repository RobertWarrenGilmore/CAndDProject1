public class DummyPM extends DummyEmployee {

    private SimulationClock.Stopwatch answeringQuestionsWatch = new SimulationClock.Stopwatch();

    public DummyPM(String name) {
        super("PM", name);
    }

    public void askQuestion() {
        synchronized (this) {
            log("receives a question");
            answeringQuestionsWatch.start();

            // The buck stops here, but give it ten minutes.
            SimulationClock.waitMinutes(10);

            answeringQuestionsWatch.pause();
            log("answers a question");
        }
    }

    public int getTimeSpentAnsweringQuestions() {
        return answeringQuestionsWatch.totalTimeElapsed();
    }

    @Override
    protected void doMorningWork() {
        
    }

    @Override
    protected void doAfternoonWork() {

    }
}
