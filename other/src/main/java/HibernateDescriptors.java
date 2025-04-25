@Entity
@Table(name = "")
@Getter @Setter @NoArgsConstructor
public class DocumentValidationRecord extends BaseEntity {

    @Column(name = "resolved_p")
    private boolean resolved = false;

    @Column(name = "text_p")
    private String text;

    @Column(name = "type_p")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_person_id")
    private PersonalData author;

    @Column(name = "document_id", nullable = false)
    private Long documentId;

    @Column(name = "document_type", length = 3, nullable = false)
    private String documentType;

    public void attachTo(AbstractDocumentData document) {
        this.documentId = document.getId();
        this.documentType = document.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}

@MappedSuperclass
@Getter @Setter
@DiscriminatorColumn(name = "document_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractDocumentData extends BaseEntity {

}

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("PAS")
@Table(name = "")
public class PassportData extends AbstractDocumentData {

}

DocumentValidationRecord record = new DocumentValidationRecord();
record.attachTo(passportData);
record.setText("comment");
record.setAuthor(userContext.getUser().getPersonalData());
dao.save(record);