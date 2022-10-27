import java.util.*;

public class Performer implements CollectionTest
{

    private int size;
    private int iterations;

    private LinkedList<Person> linkedList;
    private ArrayList<Person> arrayList;
    private HashMap<Person,Person> hashMap;

    public Performer()
    {

    }

    public void setSize(int size)
    {
        this.size = size;
        System.out.println("Size was set to:"+size);
    }

    public void runTest(CollectionType type, TestType test, int iterations)
    {
        this.iterations = iterations;

        if(test == TestType.ADD     && type == CollectionType.LINKED_LIST   ) liAdd();
        if(test == TestType.ADD     && type == CollectionType.ARRAY_LIST    ) arAdd();
        if(test == TestType.ADD     && type == CollectionType.HASH_MAP      ) haAdd();
        if(test == TestType.INDEX   && type == CollectionType.LINKED_LIST   ) liIndex();
        if(test == TestType.INDEX   && type == CollectionType.ARRAY_LIST    ) arIndex();
        if(test == TestType.INDEX   && type == CollectionType.HASH_MAP      ) haIndex();
        if(test == TestType.SEARCH  && type == CollectionType.LINKED_LIST   ) liSearch();
        if(test == TestType.SEARCH  && type == CollectionType.ARRAY_LIST    ) arSearch();
        if(test == TestType.SEARCH  && type == CollectionType.HASH_MAP      ) haSearch();
    }

    private void liAdd()
    {
        for(int i=0;i<iterations;i++)
        {
            linkedList = new LinkedList<Person>();
            for(int j=0;j<size;j++)
            {
                Person nextHuman = new Person("Human"+j, j+18);
                linkedList.add(nextHuman);
            }
        }
    }

    private void arAdd()
    {
        for(int i=0;i<iterations;i++)
        {
            arrayList = new ArrayList<Person>();
            for(int j=0;j<size;j++)
            {
                Person nextHuman = new Person("Human"+j, j+18);
                arrayList.add(nextHuman);
            }
        }
    }

    private void haAdd()
    {
        for(int i=0;i<iterations;i++)
        {
            hashMap = new HashMap<Person,Person>();
            for(int j=0;j<size;j++)
            {
                Person nextHuman = new Person("Human"+j, j+18);
                hashMap.put(nextHuman,nextHuman);
            }
        }
    }

    private void liIndex()
    {

    }

    private void arIndex()
    {

    }

    private void haIndex()
    {

    }

    private void liSearch()
    {

    }

    private void arSearch()
    {

    }

    private void haSearch()
    {

    }



}
