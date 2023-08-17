package forsyde.io.frontsyde.sy

class SY {

    def static map(Map<String, Object> ports, @DelegatesTo(SYProcessDef) closure) {
        SYProcessDef syProcessDef = new SYProcessDef()
        def code = closure.rehydrate(syProcessDef, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        return syProcessDef
    }
}
