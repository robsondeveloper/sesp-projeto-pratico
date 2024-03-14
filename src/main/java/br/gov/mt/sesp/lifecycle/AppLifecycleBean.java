package br.gov.mt.sesp.lifecycle;

import br.gov.mt.sesp.mapper.EnderecoMapper;
import br.gov.mt.sesp.mapper.PessoaMapper;
import br.gov.mt.sesp.model.Endereco;
import br.gov.mt.sesp.model.Pessoa;
import br.gov.mt.sesp.repository.EnderecoRepository;
import br.gov.mt.sesp.repository.PessoaRepository;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.time.Month;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger logger = Logger.getLogger("AppLifecycleBean");

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaMapper pessoaMapper;
    private final EnderecoMapper enderecoMapper;

    public AppLifecycleBean(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository,
                            PessoaMapper pessoaMapper, EnderecoMapper enderecoMapper) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
        this.pessoaMapper = pessoaMapper;
        this.enderecoMapper = enderecoMapper;
    }

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        logger.info("The application is starting...");
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("kratos");
        pessoa.setCpf("87707873017");
        pessoa.setMae("mae");
        pessoa.setNascimento(LocalDate.of(1980, Month.JANUARY, 30));
        pessoa.setTelefone("65912345678");
        pessoaRepository.persist(pessoa);

        Endereco endereco = new Endereco();
        endereco.setBairro("pedregal");
        endereco.setCep("78068-987");
        endereco.setCidade("Cuiabá");
        endereco.setEstado("MT");
        endereco.setNumero(123);
        endereco.setPessoa(pessoa);
        enderecoRepository.persist(endereco);

        Pessoa atreus = new Pessoa();
        atreus.setNome("Atreus");
        atreus.setCpf("53266142004");
        atreus.setMae("Laufey");
        atreus.setNascimento(LocalDate.of(2008, Month.MARCH, 20));
        atreus.setTelefone("65955446688");
        pessoaRepository.persist(atreus);
    }

    void onStop(@Observes ShutdownEvent ev) {
        logger.info("The application is stopping...");
    }

}