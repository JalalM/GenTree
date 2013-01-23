import java.io.File;

public class GeneralTreeNode implements Comparable {
	static int MAXIMUM = 10;
	File dir;
        Comparable key;
	int count = 0;
	GeneralTreeNode childs[];
	GeneralTreeNode parent;
        
	public GeneralTreeNode(){
            childs = new GeneralTreeNode[MAXIMUM];
	}
        public GeneralTreeNode(File f){
            dir=f;
            key=dir.getName();
            childs = new GeneralTreeNode[MAXIMUM];
	}
        
	public int compareTo(Object a) {
           GeneralTreeNode b = (GeneralTreeNode)a;
            if(this.getClass()==b.getClass()){
            return this.key.toString().compareTo(b.key.toString());
           }
           return 1;
        }
    @Override
	public String toString(){
		return "" + key;
	}

	public void visit() {
        System.out.print(key + " ");
    }
}