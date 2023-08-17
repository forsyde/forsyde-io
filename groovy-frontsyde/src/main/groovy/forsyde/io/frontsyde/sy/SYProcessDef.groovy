package forsyde.io.frontsyde.sy

class SYProcessDef {

    String identifier_
    Set<String> portNames_ = new HashSet<>()
    HashMap<String, Class<?>> portTypes = new HashMap<>()
    Object body_

}
