package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {
}
