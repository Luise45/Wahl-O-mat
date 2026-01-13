package htw.gruppe.backend.record;

/**
 * Dto für KandidatenAntworten get Endpunkt in Controller Klasse
 * @param aussageId
 * @param answerValue
 */
public record KandidatenAntwortenDto(
  Long aussageId,
  int answerValue ){
    
}

