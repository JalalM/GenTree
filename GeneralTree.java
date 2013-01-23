import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFileChooser;


public class GeneralTree {
        GeneralTreeNode root;
	
	public GeneralTree(){
		root = null;
	}
	
	public GeneralTree(File f){
		root=new GeneralTreeNode(f);
	}
	
	public GeneralTree(GeneralTreeNode el){
		root = el;
	}
        //Open File from the Tree
        public static void open(File document) throws IOException {
            Desktop dt = Desktop.getDesktop();
            dt.open(document);
}
	//finish
	public void insert(GeneralTreeNode parentNode, GeneralTreeNode newNode){
		if(!check(parentNode))
			TreeStructure.jFrame.jTextArea1.setText(TreeStructure.jFrame.jTextArea1.getText()+"the node "+parentNode.key+" doesn't exist\n");
		else{
                    GeneralTreeNode g= search(parentNode.key.toString());
                    if(g.count < 10){
                    newNode.parent = g;
                    g.childs[g.count] = newNode;
                    g.count++;
		}
                else
			TreeStructure.jFrame.jTextArea1.setText(TreeStructure.jFrame.jTextArea1.getText()+"The node: "+parentNode.key+" has its maximum children\n");
                }
		
	}      
	//finished
	public boolean check(GeneralTreeNode parentNode){
            GeneralTreeNode g = search(parentNode.key.toString());
            if(g!=null) 
                return true;
            else 
                return false;
        }
        //finished
	public GeneralTreeNode search(String newNode){
		GeneralTreeNode p = root;
        Queue queue = new Queue();
        if (p != null) {
             queue.enqueue(p);
             while (!queue.isEmpty()) {
                 p = (GeneralTreeNode) queue.dequeue();
                 if(p.key.toString().equalsIgnoreCase(newNode))
                	 return p;
                 else{
                	 if(p.count == 0)
                		continue ;
                	 else for(int i=0;i<p.count;i++)
                		 if(p.childs[i]!=null)
                			 queue.enqueue(p.childs[i]);
                 }
             }
        }
		
		return null;
	}	
        //finished
	public boolean delete(GeneralTreeNode e){
            GeneralTreeNode en = search(e.key.toString());
            if(en!=null){
                for(int i = 0; i < en.parent.count ; i++){
                    if(e.key.toString().equals(en.parent.childs[i].toString())){
                        int j;
                        for( j = i ; j<en.parent.count-1; j++)
                            en.parent.childs[j] = en.parent.childs[j+1];
                        en.parent.childs[j] = null;
                        en.parent.count--;
                        return true;
                        }
                }
                return false;
            }
            else return false;
}
        //finished
	public void sortByLevel(){
             GeneralTreeNode p = root;
             Queue queue = new Queue();
            if (p != null) {
                queue.enqueue(p);
                while (!queue.isEmpty()){
                 p = (GeneralTreeNode) queue.dequeue();
                 if(p.count>=2)
                    Arrays.sort(p.childs,0,p.count);
                 for(int i = 0; p.childs[i]!=null&&i<p.count-1; i++)
                	 queue.enqueue(p.childs[i]);
                 
                }
            }
             System.out.println("Sorted");
        }
        //finished
        public static File selectedFile(){
               JFileChooser chooser = new JFileChooser();
               chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       int returnVal = chooser.showOpenDialog(null);
			if ( returnVal == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile() ;
    }
                        else return null;
        }	
        //finished
        public void addFiles(File file){
            Queue queue = new Queue();
            GeneralTreeNode nodeT = new GeneralTreeNode(file);
            queue.enqueue(nodeT);

            while (!queue.isEmpty()) {
                GeneralTreeNode gtn = (GeneralTreeNode) queue.dequeue();
                File list[] = gtn.dir.listFiles();
                    if(list.length!=0){
                	 for(int i=0;i<list.length;i++){
                             if(list[i]!=null){
                		 GeneralTreeNode gtn1 = new GeneralTreeNode(list[i]);
                		 insert(gtn,gtn1);
                                 if(gtn1.dir.isDirectory())
                                    queue.enqueue(gtn1);
                             }
                	 }

                    }

             }
             System.out.println("the Files have been added");
	}
	//finished
	public int breadthFirst() {
        int count = 0;
        GeneralTreeNode p = root;
        Queue queue = new Queue();
        if (p != null) {
             queue.enqueue(p);
             while (!queue.isEmpty()) {
                 p = (GeneralTreeNode) queue.dequeue();
                 TreeStructure.jFrame.jTextArea1.setText(TreeStructure.jFrame.jTextArea1.getText()+p+"\t"+p.parent+"\n");

                 count++;
                 for(int i = 0; i<p.childs.length; i++){
                     if(p.childs[i]!=null)
                	 queue.enqueue(p.childs[i]);
                 }
             }
        }
        return count;
		
	}
}
