
public class Developer extends Employee {

    TeamLead boss;

    public Developer(String name, TeamLead boss) {
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
