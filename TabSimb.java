import java.util.ArrayList;
import java.util.Iterator;


public class TabSimb
{
    private ArrayList<TS_entry> lista;
    
    public TabSimb( )
    {
        lista = new ArrayList<TS_entry>();
    }
    
     public void insert( TS_entry nodo ) {
      lista.add(nodo);
    }    
    
    public void listar() {
      int cont = 0;  
      System.out.println("\n\nListagem da tabela de simbolos:\n");
      for (TS_entry nodo : lista) {
          System.out.println(nodo);
      }
    }
      
    public TS_entry pesquisa(String umId) {
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId) && nodo.getEscopo()==null) {
	          return nodo;
          }
      }
      return null;
    }

    public TS_entry pesquisa(String umId, TS_entry escopo) {
      if(escopo==null){
        return pesquisa(umId);
      }
      //primeiro busca a variável dentro do seu escopo
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId) ) {
            TS_entry escopoNodo = nodo.getEscopo();
            if(escopoNodo != null && nodo.getEscopo().getId().equals(escopo.getId()))
              return nodo;
          }
      }

      /* 
      //caso não encontre, busca no escopo global
      for (TS_entry nodo : lista) {
        if (nodo.getId().equals(umId) && nodo.getEscopo() == null) {
          return nodo;
        }
      }
      */

      return null;
    }

    public TS_entry getByIndex(int idx) {
      if(lista.size() < idx){
        return null;
      }
      return lista.get(idx);
    }
    public int getIndexOf(String umId) {
      int idx = 0;
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId)) {
	          return idx;
          }
          idx++;
      }
      return -1;
    }

    public  ArrayList<TS_entry> getLista() {return lista;}
}



