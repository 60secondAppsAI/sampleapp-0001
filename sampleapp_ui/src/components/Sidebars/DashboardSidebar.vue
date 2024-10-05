<template>
	
	<!-- Main Sidebar -->
	<a-layout-sider
		collapsible
		class="sider-primary"
		breakpoint="lg"
		collapsed-width="0"
		width="250px"
		:collapsed="sidebarCollapsed"
		@collapse="$emit('toggleSidebar', ! sidebarCollapsed)"
		:trigger="null"
		:class="['ant-layout-sider-' + sidebarColor, 'ant-layout-sider-' + sidebarTheme]"
		theme="light"
		:style="{ backgroundColor: 'transparent',}">
			<div class="brand"><img src="images/drc-velocity.png" alt=""> <br><span>sampleapp</span></div>
			<hr>

			<a-menu theme="light" mode="inline"
      			:open-keys="openKeys"
				@openChange="onOpenChange"
				>


	 
			
				<a-menu-item>
					<router-link to="/users">
						<span class="label">Users</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/issues">
						<span class="label">Issues</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/comments">
						<span class="label">Comments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/projects">
						<span class="label">Projects</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/tasks">
						<span class="label">Tasks</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/attachments">
						<span class="label">Attachments</span>
					</router-link>
				</a-menu-item>
		
	

			</a-menu>

	</a-layout-sider>

</template>

<script>

	export default ({
		props: {
			// Sidebar collapsed status.
			sidebarCollapsed: {
				type: Boolean,
				default: false,
			},
			
			// Main sidebar color.
			sidebarColor: {
				type: String,
				default: "primary",
			},
			
			// Main sidebar theme : light, white, dark.
			sidebarTheme: {
				type: String,
				default: "light",
			},
		},
		data() {
			return {
      			rootSubmenuKeys: [],
				openKeys: [],
			}
		},
		methods: {
			onOpenChange(openKeys)
			{
			
				const latestOpenKey = openKeys.find( key => this.openKeys.indexOf( key ) === -1) ;

				if ( this.rootSubmenuKeys.indexOf( latestOpenKey ) === -1 )
				{
					this.openKeys = openKeys;
				}
				else
				{
					this.openKeys = latestOpenKey ? [ latestOpenKey ] : [] ;
				}
			},
		},
	})

</script>

<style>

.drcv-label {
	padding-left: 65px;
}

</style>
