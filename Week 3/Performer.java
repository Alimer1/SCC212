import java.util.*;

public class Performer implements CollectionTest
{

    private int size;
    private int iterations;

    private Person[] personPool;
    private Person middlePerson;
    private int middlePersonIndex;
    private String middlePersonName;

    private LinkedList<Person> linkedList;
    private ArrayList<Person> arrayList;
    private HashMap<String,Person> hashMap;

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

        middlePersonIndex = size/2;
        middlePerson = personPool[middlePersonIndex];
        middlePersonName = middlePerson.getName();

        System.out.println("Size was set to:"+size);
    }

    public void runTest(CollectionType type, TestType test, int iterations)
    {
        for(int i=0;i<iterations;i++)
        {
            if(test == TestType.ADD && type == CollectionType.LINKED_LIST)
            {
                linkedList = new LinkedList<Person>();
                for(int j=0;j<size;j++)
                {
                    linkedList.add(personPool[j]);
                }
            }
            if(test == TestType.ADD && type == CollectionType.ARRAY_LIST)
            {
                arrayList = new ArrayList<Person>();
                for(int j=0;j<size;j++)
                {
                    arrayList.add(personPool[j]);
                }
            }
            if(test == TestType.ADD && type == CollectionType.HASH_MAP)
            {
                hashMap = new HashMap<String,Person>();
                for(int j=0;j<size;j++)
                {
                    hashMap.put(personPool[j].getName(),personPool[j]);
                }
            }
            if(test == TestType.INDEX && type == CollectionType.LINKED_LIST)
            {
                Person subject = linkedList.get(middlePersonIndex);
            }
            if(test == TestType.INDEX && type == CollectionType.ARRAY_LIST)
            {
                Person subject = arrayList.get(middlePersonIndex);
            }
            if(test == TestType.INDEX && type == CollectionType.HASH_MAP)
            {
                Person subject = hashMap.get(middlePersonName);
            }
            if(test == TestType.SEARCH && type == CollectionType.LINKED_LIST)
            {
                for(int j=0;j<size;j++)
                {
                    if(linkedList.get(j).getName() == middlePersonName)
                    {
                        break;
                    }
                }
            }
            if(test == TestType.SEARCH && type == CollectionType.ARRAY_LIST)
            {
                for(int j=0;j<size;j++)
                {
                    if(arrayList.get(j).getName() == middlePersonName)
                    {
                        break;
                    }
                }
            }
            if(test == TestType.SEARCH && type == CollectionType.HASH_MAP)
            {
                Person subject = hashMap.get(middlePersonName);
            }
        }
    }

}
