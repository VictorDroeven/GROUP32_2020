
import java.io.*;


public class ChromaticNumberBackTrackingAlgo {
	
	
	public final static boolean DEBUG = false;
	
	public final static String COMMENT = "//";
	
	public static int calculate( String args[] ){
		/**
		* the for loop makes sure that the main method goes through all the
		* files that it gets inputed through the terminal window.
		*/
		long start = System.currentTimeMillis();
		if( args.length < 1 )
				{
					System.out.println("Error! No filename specified.");
					System.exit(0);
				}
		for(int loops = 0; loops<args.length; loops++){				
			
			String inputfile = args[loops];
		
			boolean seen[] = null;
		
			//! n is the number of vertices in the graph
			int n = -1;
		
			//! m is the number of edges in the graph
			int m = -1;
		
			//! e will contain the edges of the graph
			ColEdge e[] = null;
		
			try { 
				FileReader fr = new FileReader(inputfile);
				BufferedReader br = new BufferedReader(fr);

				String record = new String();
				
				//! THe first few lines of the file are allowed to be comments, staring with a // symbol.
				//! These comments are only allowed at the top of the file.
				
				//! -----------------------------------------
				while ((record = br.readLine()) != null)
					{
						if( record.startsWith("//") ) continue;
						break; // Saw a line that did not start with a comment -- time to start reading the data in!
				}

				if( record.startsWith("VERTICES = ") )
				{
					n = Integer.parseInt( record.substring(11) );					
					if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
				}

				seen = new boolean[n+1];	
					
				record = br.readLine();
				
				if( record.startsWith("EDGES = ") )
				{
					m = Integer.parseInt( record.substring(8) );					
					if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
				}

				e = new ColEdge[m];	
												
				for( int d=0; d<m; d++)
				{
					if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
					record = br.readLine();
					String data[] = record.split(" ");
					if( data.length != 2 )
						{
						System.out.println("Error! Malformed edge line: "+record);
						System.exit(0);
					}
					e[d] = new ColEdge();
					
					e[d].u = Integer.parseInt(data[0]);
					e[d].v = Integer.parseInt(data[1]);

					seen[ e[d].u ] = true;
					seen[ e[d].v ] = true;
					
					if(DEBUG) System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);
			
				}
								
				String surplus = br.readLine();
				if( surplus != null )
				{
					if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
				}
			br.close();	
			}
			catch (IOException ex)
			{ 
				// catch possible io errors from readLine()
				System.out.println("Error! Problem reading file "+inputfile);
				System.exit(0);
			}

			for( int x=1; x<=n; x++ )
			{
				if( seen[x] == false )
				{
					if(DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
				}
			}
			//! At this point e[0] will be the first edge, with e[0].u referring to one endpoint and e[0].v to the other
			//! e[1] will be the second edge...
			//! (and so on)
			//! e[m-1] will be the last edge
			//! 
			//! there will be n vertices in the graph, numbered 1 to n
				
			//! INSERT YOUR CODE HERE!
			/*
			this short part of the program creates a graph variable using the graph class.
			Then fills it with all the edges given to it by the first part of the main method.
			Finally it prints out a statement with the greedyColoringAlgorithm method in it.
			*/
			StartWindow.edges = new ColEdge[m];
			StartWindow.vertices = n;
			StartWindow.length = m;
			for(int i = 0; i<m; i++) {
				StartWindow.edges[i]= new ColEdge();
				StartWindow.edges[i].u = e[i].u;
				StartWindow.edges[i].v = e[i].v;
			}
			
			
			Graph3 graph1 = new Graph3(n);
			for(int i = 0; i<m; i++){
				graph1.addEdge((e[i].u-1), (e[i].v-1));
			}
			
			for(int i = 1; i< n; i++) {
				if(BackTrackingAlgo.backTrackingAlgorithm(graph1, i, n)) {
					return i;
				}
			}
		}
		long end = System.currentTimeMillis()-start;
		System.out.print("It took " + end + " milliseconds.");
		return -1;
	}
}
