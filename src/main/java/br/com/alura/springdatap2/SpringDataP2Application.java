package br.com.alura.springdatap2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springdatap2.orm.Cargo;
import br.com.alura.springdatap2.orm.Funcionario;
import br.com.alura.springdatap2.orm.FuncionarioProjecao;
import br.com.alura.springdatap2.repository.CargoRepository;
import br.com.alura.springdatap2.repository.FuncionarioRepository;

@SpringBootApplication
public class SpringDataP2Application implements CommandLineRunner {

	@Autowired
	private CargoRepository cargoRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataP2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo = new Cargo();
		cargo.setId(1);
		cargo.setDescricao("Desenvolvedor de Sotware");

		Cargo cargo2 = new Cargo();
		cargo2.setDescricao("Desenvolvedor de Sotware Senior");

		// cargoRepository.save(cargo2);

		Funcionario f1 = new Funcionario();
		f1.setCargo(cargo);
		LocalDate data = LocalDate.now(); 
		f1.setNome("Marcos Paranhos 2");
		f1.setDataContratacao(data);
		f1.setSalario((double) 2500);
		
		Funcionario f2 = new Funcionario();
		f2.setCargo(cargo);
		LocalDate data2 = LocalDate.now(); 
		f2.setNome("Fernada Silva Alencar");
		f2.setDataContratacao(data2);
		f2.setSalario((double) 5000);

		//funcionarioRepository.saveAll(Arrays.asList(f2));

		//buscarCargos();

		//buscarFuncionarioNome();
		
		//findByNomeSalarioMaiorDataContratacao(); 
		
		//findDataCotratacaoMaiorNativeQuery(); 
		pesquisaFuncionarioSalario(); 
	}

	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario(); 
		list.forEach(f ->{
			System.out.println("Id: "+ f.getId() +" Nome: "+ f.getNome()+" R$: "+f.getSalario());
		});
	}

	private void findDataCotratacaoMaiorNativeQuery() {
		LocalDate data2 = LocalDate.now(); 
		List<Funcionario> funcionarios = funcionarioRepository
				.findDataCotratacaoMaior(data2);
		
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
	}

	private void findByNomeSalarioMaiorDataContratacao() {
		LocalDate data2 = LocalDate.now(); 
		List<Funcionario> funcionarios = funcionarioRepository
				.findByNomeSalarioMaiorDataContratacao("Marcos Paranhos 2", (double)3.4,data2);
		
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
	}

	private void buscarCargos() {
		Iterable<Cargo> cargos = cargoRepository.findAll();

		for (Cargo cargo3 : cargos) {
			System.out.println(cargo3.getDescricao());
		}
	}

	private void buscarFuncionarioNome() {
		System.out.println(funcionarioRepository.findByNomeLike("%Rodrigo%"));

	}

}
