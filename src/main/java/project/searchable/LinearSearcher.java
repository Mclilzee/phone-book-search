package project.searchable;

public class LinearSearcher extends Searcher {

    public LinearSearcher(Contact[] searchableContacts, Contact[] toFind) {
        super(searchableContacts, toFind);
    }

    @Override
    public String search() {
        findElements();
        return String.format("Start searching (linear search)...\n%s", getFoundMessage());
    }

    @Override
    boolean isElementInSearchableFile(Contact element) {
        for (Contact contact : this.searchableContacts) {
            if (contact.equals(element)) {
                return true;
            }
        }

        return false;
    }
}
