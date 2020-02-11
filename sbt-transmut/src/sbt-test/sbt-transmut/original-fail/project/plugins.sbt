sys.props.get("plugin.version") match {
  case Some(x) => addSbtPlugin("br.ufrn.dimap.forall" % "sbt-transmut" % x)
  case _ => sys.error("""|The system property 'plugin.version' is not defined.
                         |Specify this property using the scriptedLaunchOpts -D.""".stripMargin)
}