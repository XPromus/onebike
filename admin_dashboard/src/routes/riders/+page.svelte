<script lang="ts">
	import TableRow from '$lib/components/table/TableRow.svelte';
	import TableCell from '$lib/components/table/TableCell.svelte';
	import TableHeader from '$lib/components/table/TableHeader.svelte';
	import type { PageData } from './$types';
	import RiderEdit from '$lib/components/edit/RiderEdit.svelte';
	import Panel from '$lib/components/Panel.svelte';
	import type { RiderWithChildren, RiderWithIds } from '$lib/types/client/rider.types';
	import { riderWithChildrenToRiderWithIds } from '$lib/middleware/rider.mapper';
	
    let { 
        data 
    }: { 
        data: PageData 
    } = $props();

    let selectedRider: RiderWithIds | null = $state<RiderWithIds | null>(null);
    const onRiderSelect = (
        rider: RiderWithChildren
    ) => {
        selectedRider = riderWithChildrenToRiderWithIds(rider);
    }
</script>

<div class="space-y-2 flex overflow-hidden relative flex-col p-2 w-full h-full">
    <div class="flex flex-1 flex-col min-h-0 space-y-2">
        <div class="flex flex-row shrink-0 bg-mauve-0">
            <TableHeader content="Name" />
            <TableHeader content="Nationality" />
            <TableHeader content="Team" />
            <TableHeader content="Birthday" />
        </div>
        <div class="flex overflow-y-scroll flex-col flex-1 min-h-0 space-y-1">
            {#each data.riders as rider }
                <TableRow>
                    <TableCell content={`${rider.firstName} ${rider.lastName}`} />
                    <TableCell content={`${rider.nation.flagEmoji} ${rider.nation.shortName}`} />
                    <TableCell content={rider.team == null ? "--" : rider.team.teamName} />
                    <TableCell content={rider.dateOfBirth.toLocaleDateString("de-DE")} />
                </TableRow>
            {/each}
        </div>
    </div>
    <Panel>
        <RiderEdit selectedRider={selectedRider} nations={data.nations} teams={data.teams} />
    </Panel>
</div>
