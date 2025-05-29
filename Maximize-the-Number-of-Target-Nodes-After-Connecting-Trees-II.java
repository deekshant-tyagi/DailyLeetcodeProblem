class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n1=edges1.length+1, n2=edges2.length+1;
        ArrayList<ArrayList<Integer>> graph1=new ArrayList<>();
        ArrayList<ArrayList<Integer>> graph2=new ArrayList<>();
        for(int i=0;i<n1;i++){
            graph1.add(new ArrayList<>());
        }
        for(int i=0;i<n2;i++){
            graph2.add(new ArrayList<>());
        }
        for(int edge1[]:edges1){
            int u=edge1[0];
            int v=edge1[1];
            graph1.get(u).add(v);
            graph1.get(v).add(u);
        }
        for(int edge2[]:edges2){
            int u=edge2[0];
            int v=edge2[1];
            graph2.get(u).add(v);
            graph2.get(v).add(u);
        }
        //for graph 1 find for every node how many neighbours they have in odd distance and even distance
        //because for a parent to even nodes he needs child's odd nodes
        //we have to do in O(N) i.e. one traversal, filling answers in an array
        int oddSubtree1[]=new int[n1];
        int evenSubtree1[]=new int[n1];
        int totalOdd1[]=new int[n1];
        int totalEven1[]=new int[n1];
        int oddSubtree2[]=new int[n2];
        int evenSubtree2[]=new int[n2];
        int totalOdd2[]=new int[n2];
        int totalEven2[]=new int[n2];
        boolean visited1[]=new boolean[n1];
        boolean visited2[]=new boolean[n2];
        dfs1Tree1(graph1,0,oddSubtree1,evenSubtree1,visited1);
        totalEven1[0]=evenSubtree1[0];
        totalOdd1[0]=oddSubtree1[0];
        visited1=new boolean[n1];
        dfs2Tree1(graph1,0,oddSubtree1,evenSubtree1,visited1,totalEven1,totalOdd1);
      
        dfs1Tree2(graph2,0,oddSubtree2,evenSubtree2,visited2);
        totalEven2[0]=evenSubtree2[0];
        totalOdd2[0]=oddSubtree2[0];
        visited2=new boolean[n2];
        dfs2Tree2(graph2,0,oddSubtree2,evenSubtree2,visited2,totalEven2,totalOdd2);

        int goldenNodeOddCount=0;
        for(int i=0;i<n2;i++){
            goldenNodeOddCount=Math.max(goldenNodeOddCount,totalOdd2[i]);
        }
        int ans[]=new int[n1];
        for(int i=0;i<n1;i++){
            ans[i]=totalEven1[i]+goldenNodeOddCount;
        }
        return  ans;
    }
    public void dfs1Tree1 (ArrayList<ArrayList<Integer>> graph1,int node, int oddSubtree1[],int evenSubtree1[],boolean visited1[]){ // finds odd even count only for childres
        evenSubtree1[node]=1;
        visited1[node]=true;
        for(int nbr:graph1.get(node)){
            if(visited1[nbr])
                continue;
            dfs1Tree1(graph1,nbr,oddSubtree1,evenSubtree1,visited1);
            oddSubtree1[node]+=evenSubtree1[nbr];
            evenSubtree1[node]+=oddSubtree1[nbr];
        }
    }

    public void dfs2Tree1 (ArrayList<ArrayList<Integer>> graph1,int node, int oddSubtree1[],int evenSubtree1[],boolean visited1[],int totalEven1[],int totalOdd1[]) { // go to every child node and in 2 new arrays totalEven,totalOdd ,store their children's totalEven[nbr] = evenSubtree1[nbr]+ (oddSubTree[node]-evenSubtree1[nbr]) 
        visited1[node]=true;
        for(int nbr:graph1.get(node)){
            if(visited1[nbr])
                continue;
            totalEven1[nbr]=totalOdd1[node];
            totalOdd1[nbr]=totalEven1[node];  
            // System.out.println("Nbr "+nbr+" "+totalEven1[nbr]+" "+totalOdd1[nbr]);
            dfs2Tree1(graph1,nbr,oddSubtree1,evenSubtree1,visited1,totalEven1,totalOdd1);
        }
    }

    public void dfs1Tree2 (ArrayList<ArrayList<Integer>> graph2,int node, int oddSubtree2[],int evenSubtree2[],boolean visited2[]){ // finds odd even count only for childres
        evenSubtree2[node]=1;
        visited2[node]=true;
        for(int nbr:graph2.get(node)){
            if(visited2[nbr])
                continue;
            dfs1Tree2(graph2,nbr,oddSubtree2,evenSubtree2,visited2);
            oddSubtree2[node]+=evenSubtree2[nbr];
            evenSubtree2[node]+=oddSubtree2[nbr];
        }
    }

    public void dfs2Tree2 (ArrayList<ArrayList<Integer>> graph2,int node, int oddSubtree2[],int evenSubtree2[],boolean visited2[],int totalEven2[],int totalOdd2[]) { // go to every child node and in 2 new arrays totalEven,totalOdd ,store their children's totalEven[nbr] = evenSubtree1[nbr]+ (oddSubTree[node]-evenSubtree1[nbr]) 
        visited2[node]=true;
        for(int nbr:graph2.get(node)){
            if(visited2[nbr])
                continue;
            totalEven2[nbr]=totalOdd2[node];
            totalOdd2[nbr]=totalEven2[node];  
            // System.out.println("Nbr "+nbr+" "+totalEven1[nbr]+" "+totalOdd1[nbr]);
            dfs2Tree2(graph2,nbr,oddSubtree2,evenSubtree2,visited2,totalEven2,totalOdd2);
        }
    }


}