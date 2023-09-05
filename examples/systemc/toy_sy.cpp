
void siggen_func(int &out1, const int &inp1)
{
    out1 = inp1 + 1;
}

void report_func(const int &inp1)
{
    std::cout << "output value: " << inp1 << std::endl;
}

void add_func(int &out1, const int &inp1, const int &inp2)
{
    out1 = inp1 + inp2;
}

void mul_func(int &out1, const int &inp1, const int &inp2)
{
    out1 = inp1 * inp2;
}

SC_MODULE(mulacc)
{
    SY::in_port<int> a, b;
    SY::out_port<int> result;

    SY::signal<int> addi1, addi2, acci;

    SC_CTOR(mulacc)
    {
        SY::make_scomb2("mul1", mul_func, addi1, a, b);

        auto add1 = SY::make_scomb2("add1", add_func, acci, addi1, addi2);
        add1->oport1(result);

        SY::make_sdelay("accum", 0, addi2, acci);
    }
};

SC_MODULE(top)
{
    SY::signal<int> srca, srcb, result;

    SC_CTOR(top)
    {
        SY::make_sconstant("constant1", 3, 10, srca);

        SY::make_ssource("siggen1", siggen_func, 1, 10, srcb);

        auto mulacc1 = new mulacc("mulacc1");
        mulacc1->a(srca);
        mulacc1->b(srcb);
        mulacc1->result(result);

        SY::make_ssink("report1", report_func, result);
    }
};

int sc_main(int argc, char **argv)
{
    top top1("top1");

    sc_start();

    return 0;
}