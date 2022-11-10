import java.util.*;

public class Performer implements CollectionTest
{

    private int size;

    private LinkedList<Person> linkedList;
    private ArrayList<Person> arrayList;
    private HashMap<String,Person> hashMap;

    public Performer()
    {}

    public void setSize(int size)
    {
        this.size = size;
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
                    linkedList.add(new Person("Human-"+j,j+18));
                }
            }

            if(test == TestType.ADD && type == CollectionType.ARRAY_LIST)
            {
                arrayList = new ArrayList<Person>();
                for(int j=0;j<size;j++)
                {
                    arrayList.add(new Person("Human-"+j,j+18));
                }
            }

            if(test == TestType.ADD && type == CollectionType.HASH_MAP)
            {
                hashMap = new HashMap<String,Person>();
                for(int j=0;j<size;j++)
                {
                    hashMap.put("Human-"+j,new Person("Human-"+j,j+18));
                }
            }

            if(test == TestType.INDEX && type == CollectionType.LINKED_LIST)
            {
                Person subject = linkedList.get(size/2);
            }

            if(test == TestType.INDEX && type == CollectionType.ARRAY_LIST)
            {
                Person subject = arrayList.get(size/2);
            }

            if(test == TestType.INDEX && type == CollectionType.HASH_MAP)
            {
                Person subject = hashMap.get("Human-"+size/2);
            }

            if(test == TestType.SEARCH && type == CollectionType.LINKED_LIST)
            {
                for(int j=0;j<size;j++)
                {
                    if(linkedList.get(j).getName() == "Human-"+size/2)
                    {
                        Person subject = linkedList.get(j);
                    }
                }
            }

            if(test == TestType.SEARCH && type == CollectionType.ARRAY_LIST)
            {
                for(int j=0;j<size;j++)
                {
                    if(arrayList.get(j).getName() == "Human-"+size/2)
                    {
                        Person subject = arrayList.get(j);
                    }
                }
            }

            if(test == TestType.SEARCH && type == CollectionType.HASH_MAP)
            {
                Person subject = hashMap.get("Human-"+size/2);
            }
        }
    }
}
