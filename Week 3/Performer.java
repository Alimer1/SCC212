import java.util.*;

public class Performer implements CollectionTest
{

    private int size;
    private int iterations;

    private Person[] personPool;

    private LinkedList<Person> linkedList;
    private ArrayList<Person> arrayList;
    private HashMap<Integer,Person> hashMap;

    public Performer()
    {

    }

    public void setSize(int size)
    {
        this.size = size;
        personPool = new Person[size];
        for(int i=0;i<size;i++)
        {
            personPool[i] =  new Person("Human-"+i,i+18);
        }
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
                linkedList.add(personPool[j]);
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
                arrayList.add(personPool[j]);
            }
        }
    }

    private void haAdd()
    {
        for(int i=0;i<iterations;i++)
        {
            hashMap = new HashMap<Integer,Person>();
            for(int j=0;j<size;j++)
            {
                hashMap.put(personPool[j].hashCode(),personPool[j]);
            }
        }
    }

    private void liIndex()
    {
        for(int i=0;i<iterations;i++)
        {
            Person subject = linkedList.get(size/2);
            int subjectAge = subject.getAge();
        }
    }

    private void arIndex()
    {
        for(int i=0;i<iterations;i++)
        {
            Person subject = arrayList.get(size/2);
            int subjectAge = subject.getAge();
        }
    }

    private void haIndex()
    {
        for(int i=0;i<iterations;i++)
        {
            Person subject = hashMap.get(personPool[size/2].hashCode());
            int subjectAge = subject.getAge();
        }
    }

    private void liSearch()
    {
        for(int i=0;i<iterations;i++)
        {
            String targetName = personPool[size/2].getName();
            int targetAge;
            for(int j=0;j<size;j++)
            {
                if(linkedList.get(j).getName() == targetName)
                {
                    Person target = linkedList.get(j);
                    targetAge = target.getAge();
                    break;
                }
            }
        }
    }

    private void arSearch()
    {
        for(int i=0;i<iterations;i++)
        {
            String targetName = personPool[size/2].getName();
            int targetAge;
            for(int j=0;j<size;j++)
            {
                if(arrayList.get(j).getName() == targetName)
                {
                    Person target = arrayList.get(j);
                    targetAge = target.getAge();
                    break;
                }
            }
        }
    }

    private void haSearch()
    {
        for(int i=0;i<iterations;i++)
        {
            String targetName = personPool[size/2].getName();
            int targetAge;
            for(int j=0;j<size;j++)
            {
                if(hashMap.get(personPool[j].hashCode()).getName() == targetName)
                {
                    Person target = hashMap.get(personPool[j].hashCode());
                    targetAge = target.getAge();
                    break;
                }
            }
        }
    }



}
