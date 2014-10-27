
public class DummyDeveloper extends DummyEmployee {

    TeamLead boss;

    public DummyDeveloper(String name, TeamLead boss) {
        super("developer", name);
        this.boss = boss;
    }

    @Override
    protected void doMorningWork() {

    }

    @Override
    protected void doAfternoonWork() {

    }
}
