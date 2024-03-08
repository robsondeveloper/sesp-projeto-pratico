package br.gov.mt.sesp.lifecycle;

import br.gov.mt.sesp.dto.pessoa.PessoaRequest;
import br.gov.mt.sesp.mapper.PessoaMapper;
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
    private final PessoaMapper pessoaMapper;

    public AppLifecycleBean(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
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
    }

    void onStop(@Observes ShutdownEvent ev) {
        logger.info("The application is stopping...");
    }

}