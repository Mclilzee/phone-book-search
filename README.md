# Phonebook Search
Searching algorithms are put to the test. A phone book provided with a list of names to look for thousands of entries loaded and testing to see which testing algorithm is the fastest or take the shortest.

Phonebook format is Number followed by the contact name, space separated, the name can contain as many spaces as required.
A Large phonebook both sorted and unsorted are provided. To test for sorting and for searching an already sorted phonebook. The list to be found of 500 name entries are provided in `find.txt` file.

Disclaimer: This is not meant to test overall of algorithms, if an algorithm performs better in this test, it doesn't mean that algorithm will always perform better. It only mean in this particular test in searching the phone book, it is the better algorithm to use. Changing phone book data might change the result.

### Example phonebook format
```txt
023487234 Mark Azmar
23423942 John Doe
```

### Example find list
```txt
Mark
John Doe
```

# Requirement
- Java version 17+ <a href="https://www.oracle.com/de/java/technologies/downloads/">Java download Link</a>

# Build - Run Project
- Clone repository and navigate into repo's directory
- Run project `$ ./gradlew run --args="path/to/phonebook path/to/search/file"`
- `--args=""` takes arguments separated by spaces, if your path file include spaces in the name, use single quotes.
  Example: `--args="'File with space in name' other/argument`

# Example
```console
$ ./gradlew run --args="phonebook.txt find.txt"

> Task :run
Start searching (linear search)...
Found 500 / 500 entries. Time taken: 0 min. 3 sec. 44 ms.

Start searching (bubble sort + jump search)...
Found 500 / 500 entries. Time taken: 0 min. 5 sec. 755 ms.
Sorting time: 0 min. 3 sec. 44 ms. - STOPPED, moved to linear search
Searching time: 0 min. 2 sec. 711 ms.

Start searching (quick sort + jump search)...
Found 500 / 500 entries. Time taken: 0 min. 1 sec. 311 ms.
Sorting time: 0 min. 1 sec. 287 ms.
Searching time: 0 min. 0 sec. 24 ms.

Start searching (quick sort + binary search)...
Found 500 / 500 entries. Time taken: 0 min. 1 sec. 135 ms.
Sorting time: 0 min. 1 sec. 134 ms.
Searching time: 0 min. 0 sec. 1 ms.

Start searching (hash table search)...
Found 500 / 500 entries. Time taken: 0 min. 0 sec. 320 ms.
Creating time: 0 min. 0 sec. 319 ms.
Searching time: 0 min. 0 sec. 1 ms.
```
