<?php

namespace ReglementBundle\Form;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use FOS\CKEditorBundle\Form\Type\CKEditorType;
class ReglementType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
       // $builder->add('dateRegl')->add('fraisExpert')
           // ->add('modeRegl')->add('montantRegl')->add('cinassureur')->add('idrep')->add('codeSin')->add('idex');
   $builder->add('content',CKEditorType::class, [
       'attr' => ['id' => 'reglementbundle_reglement_content'],
   ]);

    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ReglementBundle\Entity\Reglement'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'reglementbundle_reglement';
    }


}
