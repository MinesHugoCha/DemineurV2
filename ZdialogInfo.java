public class ZdialogInfo {
	private String  difficulte;

	public ZdialogInfo(){}
	public ZdialogInfo(String difficulte){
		this.difficulte = difficulte;
		System.out.println(difficulte);
	}
	public String getDifficulte() {
		return difficulte;
	}
	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}

	/*public String toString(){
    String str;
    if(this.age != null){
      str = "Description de l'objet InfoZDialog";
      str += "Age : " + this.age + "\n";
    }
    else{
      str = "Aucune information !";
    }
    return str;
  }*/
}
