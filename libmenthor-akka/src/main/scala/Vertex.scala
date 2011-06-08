package menthor.akka.processing

abstract class Vertex[Data] {
  private[processing] var currentStep: Step[Data] =
    update().first

  private[processing] def moveToNextStep() {
    currentStep = currentStep.next getOrElse currentStep.first
  }

  protected implicit def mkSubstep(block: => List[Message[Data]]): Step[Data] =
    new Substep(block _)

  protected def update(): Step[Data]
}
