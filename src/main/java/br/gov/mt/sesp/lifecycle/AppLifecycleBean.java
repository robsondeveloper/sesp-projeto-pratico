package br.gov.mt.sesp.lifecycle;

import br.gov.mt.sesp.dto.endereco.EnderecoRequest;
import br.gov.mt.sesp.dto.pessoa.PessoaRequest;
import br.gov.mt.sesp.mapper.EnderecoMapper;
import br.gov.mt.sesp.mapper.PessoaMapper;
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
        PessoaRequest request = new PessoaRequest();
        request.setNome("kratos");
        request.setCpf("87707873017");
        request.setMae("mae");
        request.setNascimento(LocalDate.of(1990, Month.JANUARY, 30));
        request.setTelefone("65912345678");
        var pessoa = pessoaMapper.toModel(request);
        pessoaRepository.persist(pessoa);

        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setBairro("pedregal");
        enderecoRequest.setCep("78068-987");
        enderecoRequest.setCidade("Cuiabá");
        enderecoRequest.setEstado("MT");
        enderecoRequest.setNumero(123);
        enderecoRequest.setPessoaId(pessoa.getId());
        var endereco = enderecoMapper.toModel(enderecoRequest);
        enderecoRepository.persist(endereco);
    }

    void onStop(@Observes ShutdownEvent ev) {
        logger.info("The application is stopping...");
    }

}