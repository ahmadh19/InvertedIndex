To run the MapReduce job, do the following:
1. Export the project as a jar file, specifying the main class as "edu.wlu.cs.hadoop.invertedindex.InvertedIndex".
2. Create a cluster on Google Dataproc.
3. Add a job on Dataproc. 
	a. Specify the cluster as the cluster you just created. 
	b. Select "Hadoop" as the job type.
	c. For the arguments, the first argument should be the directory of YOUR bucket that contains the input files.
	   The second argument should be the directory of the output bucket that you created. Make sure that this output
	   directory is unique for every job, otherwise the job will fail.

	   
To run the querying program, do the following:
1. Export the program as a jar file.
2. From within the terminal:
	a. "cd" to the directory containing your jar file.
	b. Run the following command: "java -jar jarFileName.jar arg1 arg2", where
		arg1 = the directory + file name of the inverted index file, e.g. /Users/hammad/Downloads/output.txt
		arg2 = queryWord