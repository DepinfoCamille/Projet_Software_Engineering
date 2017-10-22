
public class ArbreBinaire {
    Noeud racine;
	public class Point{
		int dim;
		int T[]= new int[dim];	/*crée un tableau de dim entiers et l'initialise à 0*/	
	}
	
	
	public void set(int x,int p) {
		x=p;
	}
	
	
	public void ConstructeurPoint(int dim, Point P,int tab[]){
		int i;
		for(i=0; i<dim;i++) {
			set(tab[i],P.T[i]);
		}
	}
	
	
	private class Noeud{
        Noeud filsGauche;
        Noeud filsDroit;
        Point point;
        int indiceDuPoint;
    }
	
	
	public void ConstructeurNoeud(Noeud noeud, Point point) {
		noeud.filsGauche=null;
		noeud.filsDroit=null;
		noeud.point=point;
	}
	
/*cas où dim=1*/
	
	private void Inserer1(Point P, Noeud racine) {
	    if(racine.point==null){
	    	racine.point =P;	
	    }	
	    else {
	    	if(racine.point.T[0]> P.T[0]) {
	    		Inserer1(P,racine.filsGauche);
	    	}
	    	else {
	    		Inserer1(P,racine.filsDroit);
	    	}	
	    }
	}
	
	
	
/*cas où dim=3*/					
	
	private void Inserer(Point P, Noeud racine){
		Noeud Pere;
		Point M;
		if(racine.point==null) {
		    racine.point=P;
		}
		else {
			Pere=this.racine;
			M=Pere.point;
			if(P.T[0]<M.T[0]) {
				if(Pere.filsGauche==null) {
				     Pere.filsGauche.point=P;
				}
				else
				{
					if(P.T[1]<M.T[1]) {
						Pere=Pere.filsGauche;
				    	 if(Pere.filsGauche==null) {
				    		 Pere.filsGauche.point=P;
				    	 }
				    	 else
				    	 {
				    		 if(P.T[2]<M.T[2]) {
				    			 Pere=Pere.filsGauche;
				    			 if(Pere.filsGauche==null) {
				    				 Pere.filsGauche.point=P;
				    			 }
				    			 else {
				    				 Pere=Pere.filsGauche;
				    				 Inserer(P,Pere);
				    			 }
				    		 }
				    		 else {
				    			 Pere=Pere.filsDroit;
				    			 if(Pere.filsDroit==null) {
				    				 Pere.filsDroit.point=P;
				    			 }
				    			 else {
				    				 Pere=Pere.filsDroit;
				    				 Inserer(P,Pere);
				    			 }
				    		 }
				    			 
				    			 
				    	 }
				     }
					else {
						if(Pere.filsDroit==null) {
							Pere.filsDroit.point=P;
						}
						else {
							Pere=Pere.filsDroit;
							if(P.T[2]<M.T[2]) {
								if(Pere.filsGauche==null)
								{
									Pere.filsGauche.point=P;
								}
								else {
									Inserer(P,Pere.filsGauche);
								}
							
							}
						}
					}
				}
								/*P.T[2]>=M.T[2]*/
								
							
						
						
						
					
				
				
					/*refaire ce que j'ai fait pour la partie gauche*/
			
	}
		}
	}
	
			
			
	
	
	public ArbreBinaire addPoint( ArbreBinaire arbre, Point P) {
        if (P.dim==1) {
        	Inserer1(P,arbre.racine);
        }
        else {
        	Inserer(P,arbre.racine);
        }
		return arbre;
	}
}