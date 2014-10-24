public class TeamLead extends DummyEmployee {

    private DummyPM boss;

    public TeamLead(String name, DummyPM boss) {
        super("team lead", name);
        this.boss = boss;
    }

    /**
     * Asks a question of this team lead. The team lead might answer it or might pass the buck to the boss.
     */
    public void askQuestion() {
        synchronized (this) {
            log("receives a question");

            // Flip a coin.
            boolean answerable = (Math.random() < 0.5);
            if (!answerable) {
                // Let's ask the PM.
                log("takes a question to the head honcho");
                boss.askQuestion();
                log("gets an answer from the head honcho");
                return;
            }

            // Take your answer and go.
            log("answers a question");
            return;
        }
    }
}
