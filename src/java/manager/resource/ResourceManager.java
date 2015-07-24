package manager.resource;

import java.util.List;

public interface ResourceManager {

  public boolean add(Resource resource);

  public boolean remove(String resourceID);
  
  public boolean update(Resource resource);

  public String check(String resourceID);

  public Resource get(String resourceID);

  public List<Resource> gets();

  public List<Resource> search(String command);

  
}